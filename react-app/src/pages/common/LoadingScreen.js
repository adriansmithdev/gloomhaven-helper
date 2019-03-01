import React, { Component } from 'react';

const containerStyles = {
  height: '100vh',
  display: 'flex',
  alignItems: 'center'
};

const svgStyles = {
  width: '82px',
  height: '82px',
  alignItems: 'center',
  display: 'flex',
  flexBasis: 'auto',
  flexShrink: '0',
  flexGrow: '1'
};

class LoadingScreen extends Component {
    // Loading circle by Brent Jackson at https://github.com/jxnblk/loading
    render() {
        return (
            <div className="loading-overlay" style={containerStyles}>
                <svg style={svgStyles} xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="32" height="32" fill="white">
                  <path opacity=".25" d="M16 0 A16 16 0 0 0 16 32 A16 16 0 0 0 16 0 M16 4 A12 12 0 0 1 16 28 A12 12 0 0 1 16 4"/>
                  <path d="M16 0 A16 16 0 0 1 32 16 L28 16 A12 12 0 0 0 16 4z">
                    <animateTransform attributeName="transform" type="rotate" from="0 16 16" to="360 16 16" dur="0.8s" repeatCount="indefinite" />
                  </path>
                </svg>
            </div>
        );
    }
}


export default LoadingScreen;