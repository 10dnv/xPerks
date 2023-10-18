import { BrowserRouter, Routes, Route } from "react-router-dom";
import Profile from "./pages/Profile";
import Dashboard from "./pages/Dashboard";
import NoPage from "./pages/NoPage";
import Layout from "./pages/Layout";
import Login from "./pages/Login";
import Approve from "./pages/Approve";
import Recognize from "./pages/Recognize";
import History from "./pages/History";
import Balance from "./pages/Balance";
import Redeem from "./pages/Redeem";
import Events from "./pages/Events";
import RequireAuth from "./components/RequireAuth"

function App() {

  return (
    <BrowserRouter>
      <Routes>
        {/* public routes */}
        <Route path="/login" element={<Login />} />

        {/* protected routes */}
        <Route element={<RequireAuth />}>
          <Route path="/" element={<Layout />}>
          <Route index element={<Dashboard />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/approve" element={<Approve />} />
          <Route path="/recognize" element={<Recognize />} />
          <Route path="/history" element={<History />} />
          <Route path="/balance" element={<Balance />} />
          <Route path="/redeem" element={<Redeem />} />
          <Route path="/events" element={<Events />} />
        </Route>
        

        {/* catch the rest */}
        <Route path="*" element={<NoPage />} />

        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
