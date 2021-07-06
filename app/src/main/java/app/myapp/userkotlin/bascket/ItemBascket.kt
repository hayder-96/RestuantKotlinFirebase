package app.myapp.userkotlin.bascket

class ItemBascket (var id:String,var name:String,var image:String){


    constructor():this("","","")
    constructor(name: String,image: String):this("",name,image)
}