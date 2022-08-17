package com.example.myrappi.data.model




data class TrailerModel(
    val id: Long,
    val results: List<ResultT>
)


data class ResultT (

    val iso_639_1: String,

    val iso_3166_1: String,

    val name: String,
    val key: String,
    val site: String,
    val size: Long,
    val type: String,
    val official: Boolean,
    val published_at: String,

    val id: String
)

//enum class ISO3166_1(val value: String) {
//    Us("US");
//
//    companion object : KSerializer<ISO3166_1> {
//        override val descriptor: SerialDescriptor get() {
//            return PrimitiveSerialDescriptor("quicktype.ISO3166_1", PrimitiveKind.STRING)
//        }
//        override fun deserialize(decoder: Decoder): ISO3166_1 = when (val value = decoder.decodeString()) {
//            "US" -> Us
//            else -> throw IllegalArgumentException("ISO3166_1 could not parse: $value")
//        }
//        override fun serialize(encoder: Encoder, value: ISO3166_1) {
//            return encoder.encodeString(value.value)
//        }
//    }
//}
//
//@Serializable
//enum class ISO639_1(val value: String) {
//    En("en");
//
//    companion object : KSerializer<ISO639_1> {
//        override val descriptor: SerialDescriptor get() {
//            return PrimitiveSerialDescriptor("quicktype.ISO639_1", PrimitiveKind.STRING)
//        }
//        override fun deserialize(decoder: Decoder): ISO639_1 = when (val value = decoder.decodeString()) {
//            "en" -> En
//            else -> throw IllegalArgumentException("ISO639_1 could not parse: $value")
//        }
//        override fun serialize(encoder: Encoder, value: ISO639_1) {
//            return encoder.encodeString(value.value)
//        }
//    }
//}
//
//@Serializable
//enum class Site(val value: String) {
//    YouTube("YouTube");
//
//    companion object : KSerializer<Site> {
//        override val descriptor: SerialDescriptor get() {
//            return PrimitiveSerialDescriptor("quicktype.Site", PrimitiveKind.STRING)
//        }
//        override fun deserialize(decoder: Decoder): Site = when (val value = decoder.decodeString()) {
//            "YouTube" -> YouTube
//            else      -> throw IllegalArgumentException("Site could not parse: $value")
//        }
//        override fun serialize(encoder: Encoder, value: Site) {
//            return encoder.encodeString(value.value)
//        }
//    }
//}

