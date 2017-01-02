import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux'
import {fetchPairingList} from '../actions/actions'

export class App extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.fetchPairingList();
    }

    render() {
        return <p>This is React rendering HTML!</p>;
    }
}

App.propTypes = {
  fetchPairingList: PropTypes.func.isRequired
};

function mapStateToProps(state) {
    return {
        pairingList: state.pairingList
    }
}
export default connect(null, {fetchPairingList})(App)