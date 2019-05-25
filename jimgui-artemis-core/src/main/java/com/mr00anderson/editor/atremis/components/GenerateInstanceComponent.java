package com.mr00anderson.editor.atremis.components;


import com.artemis.Component;

/**
 * This marks a component as something to be processed for generation by the entity system to add a new type
 */
public class GenerateInstanceComponent extends Component {
    /**
     * Should we remove this component after generation
     */
    public boolean removeAfterGeneration;
}
