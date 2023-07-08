import React from 'react';
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

function LoginPage() {
  return (
    <>
      <div
        style={{
          margin: '0 auto',
          width: '900px',
        }}
      >
        <div
          style={{
            boxShadow:
              'rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px',
            backgroundColor: '#fff',
            display: 'flex',
          }}
        >
          <img src={banner} style={{ width: '60%' }} alt="Image" />
          <Form className="align-self-center">
            <FormGroup floating>
              <Input id="email" name="email" placeholder="Email" type="email" />
              <Label for="email">Email</Label>
            </FormGroup>{' '}
            <FormGroup floating>
              <Input
                id="password"
                name="password"
                placeholder="Password"
                type="password"
              />
              <Label for="password">Senha</Label>
            </FormGroup>{' '}
            <Button className="btn btn-success float-right">Entrar</Button>
          </Form>
        </div>
      </div>
    </>
  );
}

export default LoginPage;
