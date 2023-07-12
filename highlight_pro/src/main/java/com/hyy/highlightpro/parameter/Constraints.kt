package com.hyy.highlightpro.parameter

/**
 * Create by hyy on 2021/4/1
 */
sealed class Constraints {
    //vertical constraints
    object TopToTopOfHighlight : Constraints()
    object BottomToTopOfHighlight : Constraints()
    object BottomToBottomOfHighlight : Constraints()
    object TopToBottomOfHighlight : Constraints()
    //horizontal constraints
    object StartToStartOfHighlight : Constraints()
    object StartToEndOfHighlight : Constraints()
    object EndToEndOfHighlight : Constraints()
    object EndToStartOfHighlight : Constraints()
    //center constraints
    object CenterHorizontalOfHighlight : Constraints()
    object CenterVerticalOfHighlight : Constraints()

    operator fun plus(locationGravity: Constraints): List<Constraints> {
        return listOf(this, locationGravity)
    }
}
