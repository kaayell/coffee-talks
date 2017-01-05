import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux'
import {fetchHumans} from '../actions/actions'
import {Header} from "./Header"

export class Humans extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.fetchHumans();
    }

    renderHumans() {
        return this.props.humans.map(function (human, index) {
            return (
                <div key={index} className="human-container">
                    {human.name} {human.email}
                </div>
            )
        })
    }

    render() {
        return (
            <div>
                <Header />
                <div className="human-list-container">
                    {this.renderHumans()}
                </div>
            </div>)
    }
}

Humans.propTypes = {
    humans: PropTypes.array
};

function mapStateToProps(state) {
    return {
        humans: state.humans
    }
}
export default connect(mapStateToProps, {fetchHumans})(Humans)