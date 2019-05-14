import React, { Component } from 'react';



class FAQ extends Component {

    render() {
        return (
            <div>

            <h2 className="title themed-font level-item is-size-4 faq">Frequently Asked Questions</h2>
            
                <h3 className="is-size-6 has-text-weight-bold question">How to Create a Room</h3>
                    <p className="answer">To create room go to the home page. If a 
                        different scenario level and difficulty is 
                        needed then select the appropriate for the 
                        group. Click create room to get started!
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to Join a Room</h3>
                    <p className="answer">To join anothers room, wait until the room 
                        has been created by the host. Once the room 
                        has been created navigate to the home page. 
                        The host room has a special code in the top-left 
                        corner. Type that code in the join room box and 
                        press join to get started!
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to add Monsters</h3>
                    <p className="answer">To add a new monster type select it from the drop 
                        down in the top left. Next to the drop down, select 
                        if you want a normal or elite version of the monster 
                        then click +Monster button to add more monsters of that 
                        type.
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to add multiple monsters of the same type</h3>
                    <p className="answer">If a monster group already exists, more can be added with 
                        the previous method or with the + located to the right of 
                        the stats for that type.

                        Once a group has created a room then monsters can be added. 
                        Select a monster from the monster list in the top left corner. 
                        After selecting a monster the ‘+ monster’ button can be 
                        clicked to create it. Once a monster has appeared in the room 
                        there is a quick add button that appears at the top of the 
                        monster group as a ‘+’.

                        To add an elite monster toggle the elite monster toggle button
                        before hitting the ‘+ monster’ button.
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to Track Elements</h3>
                    <p className="answer">In the top right corner of the application is a series of the 6 
                        elements for the game. Click on the element icon to cycle the 
                        status between strong, waning, and inert. When the next round 
                        button is pressed the elements will automatically step down one 
                        strength level.
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to Track HP</h3>
                    <p className="answer">Once a monster has been added to the room then a monsters health
                        bar will appear in the monsters group. The bar has a token number
                        and a health bar to track each monster. Pressing the ‘-’ or ‘+’ 
                        button will change the monsters health.
                    </p>

                <h3 className="is-size-6 has-text-weight-bold question">How to track Status Effects</h3>
                    <p className="answer">After a monster has been added to the room there is a status button 
                        next to each of their health bars. Click the button and click on 
                        status effects that are active that or the next round. As the rounds 
                        progress the temporary statuses will be removed automatically.
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to remove monsters</h3>
                    <p className="answer">Next to each monster that has been added is a red ‘x’ which will delete 
                        the monster. When a monster reaches 0 health is stays on the board until 
                        the players are ready to remove it.
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to change leve/scenario</h3>
                    <p className="answer">To change the scenario level for a room click in the top right corner 
                        and type in a new number for the scenario level. When the scenario level 
                        does change the room will clear all monsters for the next encounter.
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">How to track rounds</h3>
                    <p className="answer">The current round is displayed in the top right with a button to advance 
                        to the next round. Advancing the round will also update the element 
                        tracker and status effects appropriately.
                    </p>
                <h3 className="is-size-6 has-text-weight-bold question">Where can I find monster initiatives?</h3>
                    <p className="answer">The initiative order display extends from the right side of the page. 
                        With each round the initiative display will automatically reorder the 
                        monsters based on their current actions.
                    </p>
            </div>
        );
      }

}

export default FAQ;