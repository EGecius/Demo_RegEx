package com.egecius.demo_regex

class FindAndReplacePractice {

    private fun withRegex() {


        // Replace this
        //        model.put('foo', 'bar')
        // With this
        //        model['foo'] = 'bar'
    }

    // to achieve this:
    // find: model.put\((.*),(.*)\)
    // replace: model\[$1] = $2
}
