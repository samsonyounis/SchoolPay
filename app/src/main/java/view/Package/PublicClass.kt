package view.Package

class PublicClass {
    var channelSelected:String = ""
    fun setChannel(channel:String){
        channelSelected = channel
    }

    fun getChannel():String{
        return channelSelected
    }
}