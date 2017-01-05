import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux'
import {fetchLatestPairingList, fetchNewPairs, recordPairingList} from '../actions/actions'
import {Header} from "./Header"

export class App extends Component {

    constructor(props) {
        super(props);

        this.handleNewPairsClick = this.handleNewPairsClick.bind(this);
        this.handleRecordPairsClick = this.handleRecordPairsClick.bind(this);
    }

    componentWillMount() {
        this.props.fetchLatestPairingList();
    }

    handleNewPairsClick() {
        this.props.fetchNewPairs();
    }

    handleRecordPairsClick() {
        this.props.recordPairingList(this.props.pairingListId);
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
                        <div className="pair-email">{pair.second ? pair.second.email : ""}</div>
                    </div>
                </div>
            );
        })
    }

    render() {
        return (
            <div>
                <Header />
                <div className="button-container">
                    <button onClick={this.handleNewPairsClick}>New Pairs</button>
                    <button onClick={this.handleRecordPairsClick}>Record Pairs</button>
                </div>
                <div className="pair-list-container">
                    { this.renderPairs() }
                </div>
            </div>)
    }
}

App.propTypes = {
    fetchLatestPairingList: PropTypes.func.isRequired,
    fetchNewPairs: PropTypes.func.isRequired,
    recordPairingList: PropTypes.func.isRequired,
    pairingList: PropTypes.array,
    pairingListId: PropTypes.string
};

function mapStateToProps(state) {
    return {
        pairingList: state.pairingList,
        pairingListId: state.pairingListId
    }
}
export default connect(mapStateToProps, {
    fetchLatestPairingList,
    fetchNewPairs,
    recordPairingList
})(App)