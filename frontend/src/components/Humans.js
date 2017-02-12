import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux'
import {fetchHumans, addHuman, deleteHuman} from '../actions/actions'
import {Header} from "./Header"
import {Button, Card} from 'react-materialize';

export class Humans extends Component {

    constructor(props) {
        super(props);

        this.setName = this.setName.bind(this);
        this.setEmail = this.setEmail.bind(this);
        this.handleAddHumanClick = this.handleAddHumanClick.bind(this);
        this.handleCancelHumanClick = this.handleCancelHumanClick.bind(this);
        this.handlePlusButtonClick = this.handlePlusButtonClick.bind(this);

        this.state = {
            addHumanMode: false,
            name: undefined,
            email: undefined
        };
    }

    componentWillMount() {
        this.props.fetchHumans();
    }

    setName(event) {
        this.setState({name: event.target.value});
    }

    setEmail(event) {
        this.setState({email: event.target.value});
    }

    handleAddHumanClick() {
        if (!this.state.name || !this.state.email) {
            return;
        }

        this.props.addHuman(this.state.name, this.state.email);
        this.setState({addHumanMode: false, name: undefined, email: undefined})
    }

    handleCancelHumanClick() {
        this.setState({addHumanMode: false, name: undefined, email: undefined})
    }

    handlePlusButtonClick() {
        this.setState({addHumanMode: true});
    }

    handleDeleteHumanClick(human) {
        this.props.deleteHuman(human);
    }

    renderHumans() {
        return this.props.humans.map(function (human, index) {
            return (
                <div key={index} className="human-container">
                    <Card className='human teal darken-1'
                          textClassName='white-text'
                          actions={[<a key={index} className="delete-human"
                                         onClick={this.handleDeleteHumanClick.bind(this, human)}>Remove</a>]}>
                        {human.name} <br/> {human.email}
                    </Card>
                </div>
            )
        }, this)

    }

    renderAddForm() {
        if (!this.state.addHumanMode) {
            return <Button floating className="plus-button" waves='light' icon='add'
                           onClick={this.handlePlusButtonClick}/>
        }

        return (
            <div className="add-human-form">
                <div className="input-field">
                    <input id="name" type="text" className="validate add-human-name-field"
                           onChange={this.setName}/>
                    <label htmlFor="name">Name</label>
                </div>
                <div className="input-field add-human-email-field">
                    <input id="email" type="email" className="validate"
                           onChange={this.setEmail}/>
                    <label htmlFor="email">Email</label>
                </div>
                <Button waves='light' className="add-human-button"
                        onClick={this.handleAddHumanClick}>
                    Add
                </Button>
                <Button waves='light' className="cancel-human-button"
                        onClick={this.handleCancelHumanClick}>
                    Cancel
                </Button>
            </div>)
    }

    render() {
        return (
            <div>
                <Header />
                {this.renderAddForm()}
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
    addHuman,
    deleteHuman
})(Humans)