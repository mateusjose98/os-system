import React, { useState } from 'react';
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

function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [sended, setSended] = useState(false);

  async function handleSubmit(e) {
    e.preventDefault();
    const res = await login(username, password);
    console.log(res.data);
  }

  return (
    <div className="auth-container">
      <div className="auth-banner">
        <h1>Sistema de Ordem de Serviço</h1>
        <p>Organize chamados, gere relatórios e muito mais!</p>
        <img src={banner} alt="Image" />
      </div>
      <div className="auth-form">
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
