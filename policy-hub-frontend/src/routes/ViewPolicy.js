import { useEffect, useState } from "react";
import api from "../services/api"; // Ensure API service is correctly set up
import { useNavigate } from "react-router-dom"; // For navigation

const ViewPolicy = () => {
  const [policies, setPolicies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    api.get("/policy-plan")
      .then((res) => {
        console.log("API Response:", res.data); // Debugging: Check response format
        setPolicies(res.data?.data || []); // Extract the 'data' field properly
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching policies:", err);
        setError("Unable to fetch policies. Please try again later.");
        setLoading(false);
      });
  }, []);

  const handlePurchase = (policyId) => {
    navigate(`/policy/purchase/${policyId}`); // Redirect to purchase page
  };

  return (
    <div className="container mt-4">
      <div className="card shadow p-4">
        <h2 className="text-primary text-center">Available Policies</h2>

        {loading && <p className="text-center text-secondary">Loading...</p>}
        {error && <p className="text-danger text-center">{error}</p>}
        {!loading && policies.length === 0 && <p className="text-center text-warning">No policies available.</p>}

        {policies.length > 0 && (
          <div className="table-responsive">
            <table className="table table-bordered table-striped mt-3">
              <thead className="table-primary">
                <tr>
                  <th>Policy Name</th>
                  <th>Description</th>
                  <th>Premium</th>
                  <th>Sum Insured</th>
                  <th>Term</th>
                  <th>Eligibility</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {policies.map((policy) => (
                  <tr key={policy.policyPlanId}>
                    <td>{policy.policyPlanName}</td>
                    <td>{policy.description}</td>
                    <td>₹{policy.premium}</td>
                    <td>₹{policy.sumInsured}</td>
                    <td>{policy.term}</td>
                    <td>{policy.eligibility}</td>
                    <td>
                      <button
                        className="btn btn-success"
                        onClick={() => handlePurchase(policy.policyPlanId)}
                      >
                        Purchase Policy
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default ViewPolicy;
