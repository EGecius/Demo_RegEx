package com.egecius.demo_regex;

public class FindAndReplacePractice {

    private void withRegex() {



      // Replace this
//        model.put('foo', 'bar')
    // With this
//        model['foo'] = 'bar'
    }

    // to achieve this:
    // find: model.put\((.*),(.*)\)
    // replace: model\[$1] = $2
}
