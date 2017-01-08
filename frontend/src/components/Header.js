import React, {Component} from "react";
import {Link} from "react-router";
import {Navbar} from 'react-materialize';

export class Header extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
        <Navbar brand='Coffee Talks' right>
            <Link to="/pivots">Pivots</Link>
        </Navbar>)
    }
}
