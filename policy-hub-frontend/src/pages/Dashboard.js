import { useContext } from "react";
import {useAuth} from "../context/AuthContext";
import { Link } from "react-router-dom";

const Dashboard = () => {
  const { user, logout } = useAuth();

  return (
    <div>
      <h1>Welcome, {user?.sub}</h1>
      {user?.role === "ADMIN" ? (
        <Link to="/admin">Go to Admin Panel</Link>
      ) : (
        <Link to="/policies">View Policies</Link>
      )}
      <button onClick={logout}>Logout</button>
    </div>
  );
};

export default Dashboard;
