import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../services/api";
import { useAuth } from "../context/AuthContext";

const PurchasePolicy = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { policyPlanId } = useParams();

  const [formData, setFormData] = useState({
    userId: user ? user.id : "",
    policyPlanId: policyPlanId || "",
    startDate: "",
    endDate: "",
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setMessage("");

    if (!formData.userId) {
      setMessage("⚠️ You need to register first.");
      setTimeout(() => navigate("/signup"), 3000);
      return;
    }

    try {
      await api.post("/user-policy/purchase", formData);
      setMessage("✅ Policy purchased successfully!");
      // setTimeout(() => navigate("/policy/view"), 3000);
    } catch (err) {
      setMessage("❌ Failed to purchase policy. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleClose = () => {
    navigate(-1); // Navigate back to the previous page
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <div className="d-flex justify-content-between align-items-center mb-3">
          <h2 className="text-primary text-center flex-grow-1">Purchase New Policy</h2>
          <button className="btn btn-outline-danger" onClick={handleClose}>✖ Close</button>
        </div>

        {message && <p className={`text-center fw-bold ${message.includes("❌") ? "text-danger" : "text-success"}`}>{message}</p>}

        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">User ID:</label>
            <input 
              type="number" 
              className="form-control" 
              name="userId"
              value={formData.userId} 
              onChange={handleChange}
              placeholder="Enter your User ID" 
              required 
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Policy Plan ID:</label>
            <input 
              type="number" 
              className="form-control" 
              name="policyPlanId" 
              value={formData.policyPlanId} 
              onChange={handleChange} 
              required 
            />
          </div>

          <div className="mb-3">
            <label className="form-label">Start Date:</label>
            <input type="date" className="form-control" name="startDate" onChange={handleChange} required />
          </div>

          <div className="mb-3">
            <label className="form-label">End Date:</label>
            <input type="date" className="form-control" name="endDate" onChange={handleChange} required />
          </div>

          <div className="d-flex justify-content-between">
            <button type="button" className="btn " onClick={handleClose}></button>
            <button type="submit" className="btn btn-primary" disabled={loading}>
              {loading ? "Processing..." : "Purchase Policy"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default PurchasePolicy;
