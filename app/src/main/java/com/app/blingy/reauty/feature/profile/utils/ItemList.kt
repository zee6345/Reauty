package com.app.blingy.reauty.feature.profile.utils

//fun itemList() : List<OptionItems>{
//    return listOf(
//        OptionItems(
//            "Edit Profile"
//        ),
//        OptionItems(
//            "Saved"
//        ),
//        OptionItems(
//            "Notification"
//        )
//    )
//}
object OptionsItemDataSource {

    val optionsItemList: List<OptionItems>
        get() {
            val optionsItems = ArrayList<OptionItems>()
            optionsItems.add(OptionItems("Edit Profile"))
            optionsItems.add(OptionItems("Saved"))
            optionsItems.add(OptionItems("Notification"))
            return optionsItems
        }

    val optionsItemList2: List<OptionItems>
        get() {
            val optionsItems = ArrayList<OptionItems>()
            optionsItems.add(OptionItems("Rate Us"))
            optionsItems.add(OptionItems("Share App"))
            optionsItems.add(OptionItems("Privacy Policy"))
            return optionsItems
        }
}
