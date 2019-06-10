import React from 'react';

const TopToolbar = (props) => {
  return (
    <nav className="top-topbar">
      {props.children}
    </nav>
  );
}

export default TopToolbar;