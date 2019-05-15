import React from 'react';

const ConnectionMonitor = (props) => {
  let status;
  switch(props.status) {
    case 'CONNECTION_STARTED':
      status = 'open';
      break;
    case 'NO_CONNECTION':
      status = 'pending'
      break;
    default:
      status = 'closed';
      break;

  }
  
  return (
    <div className={`connection-monitor ${status}`}
      title={`Connection: ${status}`}
      >
    </div>
  );
    
}

export default ConnectionMonitor;