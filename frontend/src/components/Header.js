import React, {Component} from "react";
import {Link} from "react-router";

export class Header extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="nav">
                <div className="nav-section">
                    <Link to="/pivots">Pivots</Link>
                </div>
                <div className="nav-section">
                    <label>Coffee Talks</label>
                </div>
                <div className="nav-section"></div>
            </div>)
    }
}
