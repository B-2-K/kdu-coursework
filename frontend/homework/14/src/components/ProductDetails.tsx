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
          {location.state.product.title}
        </div>
        <div className="lower">
          <div className="product-image">
          <img src={location.state.product.image} alt="product-image" />
          </div>
          <div className="about-product">
            <p id="model">Title : {location.state.product.title}</p>
            <p id="made-by">Category : {location.state.product.category}</p>
            <p id="price">Price : ${location.state.product.price}</p>
            <p id="desc-heading">Product Description : </p>
            <p id="desc">{location.state.product.description}</p>
            <p id="ratings-and-count"><span>Ratings : {location.state.product.rating.rate}</span> <span>Count : {location.state.product.rating.count}</span></p>
            <button type="button" onClick={handleClick}>Back To Products</button>
          </div>
        </div>
      </div>
      </div>
    </>
  );
}
