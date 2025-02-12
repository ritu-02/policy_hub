import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate for navigation
import api from "../services/api"; // Ensure correct import path for the API config

const HomeInsurance = () => {
  const [policies, setPolicies] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate(); // Hook for navigation

  useEffect(() => {
    const fetchPolicies = async () => {
      try {
        const response = await api.get("/policy-plan/plans-by-type", {
          params: { type: "HOMEINSURANCE" },
        });
        setPolicies(response.data.data);
      } catch (error) {
        console.error("Error fetching policies:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchPolicies();
  }, []);

  // Function to handle policy purchase
  const handlePurchase = (policyId) => {
    navigate(`/policy/purchase/${policyId}`); // Navigate to the purchase page with policy ID
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center text-primary">Home Insurance Plans</h2>
      {loading ? (
        <p className="text-center text-secondary">Loading...</p>
      ) : policies.length === 0 ? (
        <p className="text-center text-warning">No policies available.</p>
      ) : (
        <div className="row">
          {policies.map((policy) => (
            <div key={policy.policyPlanId || Math.random()} className="col-md-6">
              <div className="card shadow-sm p-3 mb-4">
                <h4 className="text-dark">{policy.policyPlanName || "N/A"}</h4>
                <p><strong>Description:</strong> {policy.description || "N/A"}</p>
                <p><strong>Premium:</strong> ₹{policy.premium || 0}</p>
                <p><strong>Sum Insured:</strong> ₹{policy.sumInsured || 0}</p>
                <p><strong>Term:</strong> {policy.term || "N/A"}</p>
                <p><strong>Eligibility:</strong> {policy.eligibility || "N/A"}</p>
                {/* Purchase Button */}
                <button
                  className="btn btn-primary"
                  onClick={() => handlePurchase(policy.policyPlanId)}
                >
                  Purchase Policy
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default HomeInsurance;
