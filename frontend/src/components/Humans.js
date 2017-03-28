import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux'
import {fetchHumans, addHuman, deleteHuman} from '../actions/actions'
import {Header} from "./Header"
import {Button, Card} from 'react-materialize';

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
                    <Card className='human teal darken-1'
                          textClassName='white-text'>
                        {human.name} <br/> {human.email}
                    </Card>
                </div>
            )
        }, this)

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
export default connect(mapStateToProps, {
    fetchHumans,
})(Humans)