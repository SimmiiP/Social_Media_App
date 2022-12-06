package utils

object photoNameUtility {
    @JvmStatic

    val photoNames = setOf("Travel","Event","Work","School","Party","Adventure")

    @JvmStatic

    fun isValidPhotoName(photoNameToCheck: String?): Boolean{
        for(photoName in photoNames ){
            if (photoName.equals(photoNameToCheck, ignoreCase = true)){
                return true
            }
        }
        return false
    }


}