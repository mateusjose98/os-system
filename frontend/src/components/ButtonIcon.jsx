import React from 'react';
import { Button } from 'reactstrap';
import { FaAnglesRight } from 'react-icons/fa6';
function ButtonIcon() {
  const style = { width: '100%' };
  return (
    <Button
      type="submit"
      size="lg"
      style={style}
      className="btn btn-success float-right"
    >
      Entrar <FaAnglesRight></FaAnglesRight>{' '}
    </Button>
  );
}

export default ButtonIcon;
