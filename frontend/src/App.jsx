import React from 'react';
import './index.css';
import LoginPage from './pages/Login/LoginPage';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './pages/Home/Home';
import { UserStorage } from './contexts/UserContext';
function App() {
  return (
    <>
      <div>
        <BrowserRouter>
          <UserStorage>
            <Routes>
              <Route path="/login" element={<LoginPage />} />
              <Route path="/" element={<Home />} />
            </Routes>
          </UserStorage>
        </BrowserRouter>
      </div>
    </>
  );
}

export default App;
