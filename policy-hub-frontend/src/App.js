import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Routes, Route, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import NavigationBar from './components/NavigationBar';
import  {AuthProvider}  from './context/AuthContext';
import AdminDashboard from './pages/AdminDashboard';
import ProtectedRoute from './context/ProtectedRoute';
import HomePage from './components/HomePage';
import CustomerDashboard from './pages/CustomerDashboard';
import LifeInsurance from './routes/LifeInsurance';
import HomeInsurance from './routes/HomeInsurance';
import ViewPolicy from './routes/ViewPolicy';
import PurchasePolicy from './routes/PurchasePolicy';
import Footer from './components/Footer';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';

const AppWrapper = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (performance.navigation.type === 1) {
      // Page was reloaded, redirect to Home
      navigate("/");
    }
  }, []);

  return <App />;
};

function App() {
  return (
    <AuthProvider>
      <NavigationBar />
       <div>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/policy/purchase" element={<PurchasePolicy />} />
            <Route path="/policy/purchase/:policyPlanId" element={<PurchasePolicy />} />
            <Route path="/policy/view" element={<ViewPolicy />} />
            <Route path="/insurance/life" element={<LifeInsurance />} />
            <Route path="/insurance/home" element={<HomeInsurance />} />
            <Route path="/dashboard" element={<Dashboard/>}/>
            <Route path="/admin-dashboard" element={<ProtectedRoute role="admin"><AdminDashboard /></ProtectedRoute>} />
            <Route path="/customer-dashboard" element={<ProtectedRoute role="customer"><CustomerDashboard /></ProtectedRoute>} />
            <Route path="/signin" element={<Login/>}/>
          </Routes>
       </div>
      <Footer/>
    </AuthProvider>
  );
}

// Wrap App with Router to use navigate()
export default function RootApp() {
  return (
      <AppWrapper />
  );
}
