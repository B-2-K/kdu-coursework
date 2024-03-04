import './Header.scss'
import { Link } from 'react-router-dom';

export default function Header() {
  return (
    <div className="header">
      <div className="icons-and-name">
        <div className="icons">
        <Link to={'/'}><i className="fi fi-rr-chart-histogram"></i></Link>
        </div>
        <div className="name">
          KDU Stock Market
        </div>
      </div>
      <div className="summarizer-and-portfolio">
      <div className="summarizer">
          <Link to='/summarizer'>Summarizer</Link>
        </div>
        <div className="portfolio">
          <Link to='/portfolio'>Portfolio</Link>
        </div>
      </div>
    </div>
  )
}
