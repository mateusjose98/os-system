import React, { useContext, useState } from 'react';
import {
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Row,
  Container,
  Col,
  FormFeedback,
} from 'reactstrap';

import banner from '../../assets/images/login.jpg';
import './login.css';
import ButtonIcon from '../../components/ButtonIcon';
import { login } from '../../services/Auth';
import useLocalStorage from '../../hooks/useLocalStorage';
import { UserContext } from '../../contexts/UserContext';

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [sended, setSended] = useState(false);
  const [token, setToken] = useLocalStorage('token', '');
  const { userLogin } = useContext(UserContext);

  async function handleSubmit(e) {
    e.preventDefault();
    userLogin(username, password);
  }

  return (
    <div className="auth-container custom-shadow-border">
      <div className="auth-banner">
        <h1>Sistema de Ordem de Serviço</h1>
        <p>Organize chamados, gere relatórios e muito mais!</p>
        <img src={banner} alt="Image" />
      </div>
      <div className="auth-form custom-shadow-border">
        <Form onSubmit={handleSubmit}>
          <p>Login</p>
          <FormGroup floating>
            <Input
              value={username}
              onChange={({ target }) => setUsername(target.value)}
              id="email"
              placeholder="Email"
              type="email"
            />
            <Label for="email">Email</Label>
          </FormGroup>{' '}
          <FormGroup floating>
            <Input
              id="password"
              value={password}
              onChange={({ target }) => setPassword(target.value)}
              placeholder="Password"
              type="password"
            />
            <Label for="password">Senha</Label>
          </FormGroup>{' '}
          <ButtonIcon />
        </Form>
      </div>
    </div>
  );
}

export default LoginPage;
