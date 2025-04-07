import React from 'react';
import './Homepage.css';

const HomePage = () => {
  return (
    <div className="homepage">
      <header className="header">
        <div className="logo">Science Is Tough: But So Are You</div>
        <nav className="nav">
          <input type="text" placeholder="Search..." className="search-bar" />
          <div className="nav-links">
            <a href="#chatroom">Chat Room</a>
            <a href="#notifications">Notifications</a>
            <a href="#settings">Settings</a>
          </div>
        </nav>
      </header>

      <section className="hero">
        <h1>Engage with your university journey through interactive tools and resources.</h1>
        <div className="hero-buttons">
          <button className="hero-btn">Listen to Podcasts</button>
          <button className="hero-btn">Watch Video Guides</button>
          <button className="hero-btn">Join a Study Group</button>
        </div>
      </section>

      <section className="cards-section">
        <div className="card">
          <h2>Study Skills</h2>
          <p>Learn essential study techniques to excel in your courses.</p>
          <button className="card-btn">Learn More</button>
        </div>
        <div className="card">
          <h2>Time Management</h2>
          <p>Master the art of managing your time effectively.</p>
          <button className="card-btn">Start Now</button>
        </div>
        <div className="card">
          <h2>Stress Management</h2>
          <p>Discover strategies to manage stress and maintain balance.</p>
          <button className="card-btn">Explore More</button>
        </div>
      </section>

      <footer className="footer">
        <p>&copy; 2024 Science Is Tough. All rights reserved.</p>
        <div className="footer-links">
          <a href="#contact">Contact Us</a>
          <a href="#terms">Terms of Service</a>
          <a href="#privacy">Privacy Policy</a>
        </div>
      </footer>
    </div>
  );
};

export default HomePage;
