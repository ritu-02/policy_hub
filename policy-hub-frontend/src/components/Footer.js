import { Link } from "react-router-dom";
import { FaFacebook, FaTwitter, FaLinkedin, FaInstagram } from "react-icons/fa";

const Footer = () => {
  return (
    <footer 
      className="text-dark py-3 w-100" 
      style={{ 
        backgroundColor: "#ffffff", 
        fontSize: "14px", 
        position: "fixed", 
        bottom: "0", 
        left: "0",

      }}
    >
      <div className="container text-center">
        <div className="row">
          {/* Left Section - Company Info */}
          <div className="col-md-4 mb-2">
            <h6 className="fw-bold">Policy Hub</h6>
            <p className="mb-1">Ensuring a secure future with the best insurance policies.</p>
          </div>

          {/* Center Section - Quick Links */}
          <div className="col-md-4 mb-2">
            <h6 className="fw-bold">Quick Links</h6>
            <ul className="list-unstyled">
              <li><Link to="/" className="text-dark text-decoration-none">Home</Link></li>
              <li><Link to="/policy/view" className="text-dark text-decoration-none">Policies</Link></li>
              <li><Link to="/about" className="text-dark text-decoration-none">About Us</Link></li>
              <li><Link to="/contact" className="text-dark text-decoration-none">Contact</Link></li>
            </ul>
          </div>

          {/* Right Section - Social Media Links */}
          <div className="col-md-4 mb-2">
            <h6 className="fw-bold">Follow Us</h6>
            <div className="d-flex justify-content-center">
              <a href="#" className="text-dark me-2 fs-5"><FaFacebook /></a>
              <a href="#" className="text-dark me-2 fs-5"><FaTwitter /></a>
              <a href="#" className="text-dark me-2 fs-5"><FaLinkedin /></a>
              <a href="#" className="text-dark fs-5"><FaInstagram /></a>
            </div>
          </div>
        </div>

        {/* Bottom Section */}
        <div className="mt-2">
          <p className="mb-0">&copy; {new Date().getFullYear()} Policy Hub. All Rights Reserved.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
