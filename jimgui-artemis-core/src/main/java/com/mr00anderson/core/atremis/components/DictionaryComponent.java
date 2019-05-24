package com.mr00anderson.core.atremis.components;

import com.artemis.Component;

public class DictionaryComponent extends Component {


    public String[] words;

    public DictionaryComponent(String... words) {
        this.words = words;
    }
}
