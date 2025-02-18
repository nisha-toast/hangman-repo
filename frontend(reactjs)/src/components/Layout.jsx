import React from 'react';
import { Outlet } from 'react-router';
import './Layout.css';

function Layout() {
    return (

        <div className="wrapper">
                <Outlet />
        </div>

    )
}

export default Layout;