import React, { Component } from 'react';

import IconManager from '../../common/IconManager';

class MonsterActions extends Component {  

    
    constructor(props) {
        super(props);

        this.monsterSelect = React.createRef();
        this.drawActionHandler = this.drawActionHandler.bind(this);
    }

    reshuffle(shuffle){
        if(shuffle){
            return "Shuffle Deck"
        }
    }

    drawActionHandler(event) {
      this.props.drawAction(this.props.hash, this.props.monster.id);
    }

    generateAction(action){
      // Count number of images
      let imageCounter = 0;

      const splitted = action.split(' ');

      const parsed = splitted.map((item, index) => {
        // Remove any HTML tags from item.
        const regexForHTMLTags = /(<([^>]+)>)/ig;
        const parsed = item.replace(regexForHTMLTags, '');

        // Attempt to find icon matching item
        const icon = IconManager(parsed);

        // If icon is found, use icon.
        if(icon !== undefined) {
          imageCounter++;
          if(icon.overlay !== undefined) {
            return (
              <div className="monster-action-icon-with-overlay ml-1" key={index}>
                <img className="monster-action-icon" src={icon.src} alt={icon.alt} title={icon.alt}/>
                <img className="monster-action-icon-overlay" src={icon.overlay} alt={icon.alt} title={icon.alt}/>
              </div>
            );
          } else {
            return <img className="monster-action-icon ml-1" key={index} src={icon.src} alt={icon.alt} title={icon.alt}/>;
          }
        } else {
          // If item isn't an image, return text wrapped in span without *'s
          const itemWithoutAsterisks = item.replace(/[*]/g, '');

          // If item isn't empty from removing asterisks, wrap in p tag.
          return (itemWithoutAsterisks.length > 0) ? 
            <p className="monster-action-text mr" key={index}>&nbsp;{itemWithoutAsterisks}</p> :
            '';
        }
      });
      // Only return parsed if there are images
      return (imageCounter > 0) ? 
        parsed : 
        <p className="monster-action-text">{action.replace(/[*]/g, '')}</p>;

    }



    render() {
        if(this.props.monster.monsterAction === null || this.props.monster.monsterAction === undefined) {
            return (
                <div className="monster-actions">
                    <button className="draw-action-button" 
                      onClick={this.drawActionHandler}
                      type="button">
                        Draw Action
                    </button>
                </div>
            );
        }

        // Filter out types without any instances   
        const actions = this.props.monster.monsterAction.actionDeck.map((action, index) =>
          <div className="monster-action" key={index}>
            {this.generateAction(action)}
          </div>
        );
    
        return(
            <div className="monster-actions">
                {actions}
            </div>
        );
    }
}

export default MonsterActions;