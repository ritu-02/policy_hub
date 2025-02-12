import axios from "axios";

const api = axios.create({ baseURL: "http://localhost:8081/api" });

api.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");

  // List of endpoints that require authentication
  const protectedEndpoints = ["/user", "/payment", "/claim","/admin","/auth/","/user-kyc"];

  // Check if request URL contains any protected endpoint
  if (token && protectedEndpoints.some((endpoint) => req.url.includes(endpoint))) {
    req.headers.Authorization = `Bearer ${token}`;
  }

  return req;
});




export default api;
