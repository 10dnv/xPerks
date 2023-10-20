package com.xperks.service.user;

import com.xperks.adapter.UserAdapter;
import com.xperks.dto.user.UserMainInfo;
import com.xperks.dto.user.UserModel;
import com.xperks.persistence.User;
import com.xperks.repository.user.UserRepository;
import com.xperks.security.AuthUtil;
import com.xperks.service.EntityManagerSupport;
import com.xperks.util.DocumentUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import multiversx.*;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService extends EntityManagerSupport implements UserServiceIF {

    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Override
    @Transactional
    public UserModel getUser(int id) {
        return userAdapter.toUserModel(userRepository.getUserById(id));
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("No user found with id " + id);
        }
        return user;
    }

    @Override
    @Transactional
    public UserModel findUserByEmailAddress(String emailAddress) {
        User user = userRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userAdapter.toUserModel(user);
    }

    @Override
    @Transactional
    public boolean isSuperior() {
        /* check if current user is someone's superior */
        int count = userRepository.countSuperiorById(AuthUtil.getAuthenticatedUserId());
        return count != 0;
    }

    @Override
    @Transactional
    public void changeErdAddress(String erdAddress) {
        if (StringUtils.isBlank(erdAddress)) {
            throw new IllegalArgumentException("erd address is missing");
        }
        /* change erd address for current user */
        User user = getUserById(AuthUtil.getAuthenticatedUserId());
        user.setErdAddress(erdAddress);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public List<UserMainInfo> getUserList() {
        /* get all users that can be recognized */
        List<User> users = userRepository.getListWithoutLoggedUser(AuthUtil.getAuthenticatedUserId());
        return userAdapter.toUserMainInfoList(users);
    }

    @Override
    public void redeemEgld(BigDecimal amount) throws Exception {
        User user = getUserById(AuthUtil.getAuthenticatedUserId());
        if (StringUtils.isBlank(user.getErdAddress())) {
            return;
        }
        initMxTransaction(user, amount);
    }


    private void initMxTransaction(User user, BigDecimal amount) throws Exceptions.AddressException, Exceptions.CannotDeriveKeysException, Exceptions.CannotSignTransactionException, Exceptions.CannotSerializeTransactionException, Exceptions.ProxyRequestException, IOException {
        ProxyProvider provider = new ProxyProvider("https://devnet-gateway.multiversx.com");

        multiversx.Transaction transaction = new multiversx.Transaction();
        String mnemonic = DocumentUtil.readFromInputStream(getClass().getResourceAsStream("/wallet-mnemonic.txt"));
        Wallet wallet = Wallet.deriveFromMnemonic(mnemonic.trim(), 0);
        Address walletAddress = Address.fromHex(new String(Hex.encode(wallet.getPublicKey())));
        transaction.setSender(walletAddress);
        transaction.setReceiver(Address.fromBech32(user.getErdAddress()));
        transaction.setValue((amount.multiply(new BigDecimal(10).pow(18))).toBigInteger());
        transaction.setNonce(provider.getAccount(walletAddress).getNonce());
        transaction.setGasPrice(1000000000);
        transaction.setGasLimit(50000);

        // devtest
        transaction.setChainID("D");
        transaction.sign(wallet);

        // send transaction to gateway
        provider.sendTransaction(transaction);
    }

}
