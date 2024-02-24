import { useLocation, useNavigate } from "react-router-dom";
import '../styles/ProductDetails.scss'

export default function ProductDetails() {

  const location = useLocation();
  const navigate = useNavigate();
  console.log(location.state);

  function handleClick(){
    navigate("/");
  }
  return (
    <>
      <div className="details">
      <div className="product-details-navbar">
        <div className="product-details-navbar-left">
          <input type="text" placeholder="Search.." />
          <button type="submit"><i className="fa fa-search"></i></button>
        </div>
      </div>
      <div className="product-details">
        <div className="upper">
          {location.state.product.name}
        </div>
        <div className="lower">
          <div className="product-image">
          <img src={location.state.product.image} alt="product-image" />
          </div>
          <div className="about-product">
            <p id="model">Model : {location.state.product.model}</p>
            <p id="made-by">MADE BY : {location.state.product.madeBy}</p>
            <p id="price">Price : ${location.state.product.price}</p>
            <p id="desc-heading">Product Description : </p>
            <p id="desc">{location.state.product.description}</p>
            <button type="button" onClick={handleClick}>Back To Products</button>
          </div>
        </div>
      </div>
      </div>
    </>
  );
}
