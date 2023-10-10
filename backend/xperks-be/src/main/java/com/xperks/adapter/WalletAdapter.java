package com.xperks.adapter;

import com.xperks.dto.WalletModel;
import com.xperks.persistence.Wallet;
import org.springframework.stereotype.Component;

@Component
public class WalletAdapter {

    public WalletModel toWalletModel(Wallet wallet) {
        return WalletModel
                .builder()
                .erdAddress(wallet.getErdAddress())
                .balance(wallet.getBalance())
                .build();
    }

    public Wallet toWallet(Wallet wallet) {
        return Wallet
                .builder()
                .erdAddress(wallet.getErdAddress())
                .balance(wallet.getBalance())
                .build();
    }

}
