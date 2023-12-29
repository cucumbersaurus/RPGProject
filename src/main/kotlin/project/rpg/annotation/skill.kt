package project.rpg.annotation

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CLASS
)
annotation class skill( //심심해서 추가해봄 마커 어노테이션임
    val name: String
)
