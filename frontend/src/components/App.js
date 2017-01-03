import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux'
import {fetchLatestPairingList} from '../actions/actions'
import {Header} from "./Header"

export class App extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.fetchLatestPairingList();
    }

    renderPairs() {
        return this.props.pairingList.map(function (pair, index) {
            return (
                <div key={index} className="pair-container">
                    <div className="pair">
                        <div className="pair-name">{pair.first.name}</div>
                        <div className="pair-email">{pair.first.email}</div>
                    </div>
                    <div className="pair">
                        <div className="pair-name">{pair.second ? pair.second.name : ""}</div>
                        <div className="pair-email">{pair.second ? pair.second.email: ""}</div>
                    </div>
                </div>
            );
        })
    }

    render() {
        return (
            <div>
                <Header />
                { this.renderPairs() }
            </div>)
    }
}

App.propTypes = {
    fetchLatestPairingList: PropTypes.func.isRequired,
    pairingList: PropTypes.array
};

function mapStateToProps(state) {
    return {
        pairingList: state.pairingList
    }
}
export default connect(mapStateToProps, {fetchLatestPairingList})(App)