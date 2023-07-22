import React, { useContext } from 'react';

import { UserContext } from '../../contexts/UserContext';

function Home() {
  const { data } = useContext(UserContext);
  return <div>Home {data && data.username}</div>;
}

export default Home;
