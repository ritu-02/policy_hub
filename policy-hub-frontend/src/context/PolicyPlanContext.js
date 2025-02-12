import React, { createContext, useContext, useState, useEffect } from "react";
import api from "../services/api"; // Ensure API is configured properly

// Create Context
const PolicyPlanContext = createContext();

// Custom Hook to use the Context
export const usePolicyPlans = () => useContext(PolicyPlanContext);

// Provider Component
export const PolicyPlanProvider = ({ children }) => {
  const [policyPlans, setPolicyPlans] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Function to fetch policy plans for a specific policy type
  const fetchPolicyPlans = async (policyType) => {
    setLoading(true);
    setError(null);
    try {
      const response = await api.get(`/policy-plan`);
      setPolicyPlans(response.data || []);
    } catch (err) {
      console.error("Error fetching policy plans:", err);
      setError("Failed to load policy plans");
    } finally {
      setLoading(false);
    }
  };

  return (
    <PolicyPlanContext.Provider value={{ policyPlans, loading, error, fetchPolicyPlans }}>
      {children}
    </PolicyPlanContext.Provider>
  );
};
