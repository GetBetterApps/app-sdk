package com.velkonost.getbetter.extensions

import org.gradle.api.Project
import com.velkonost.getbetter.util.getLocalProperty
import com.velkonost.getbetter.util.getKeystoreProperty
import com.velkonost.getbetter.util.getApiProperty

/**
 * Obtain property declared on `$projectRoot/local.properties` file.
 *
 * @param propertyName the name of declared property
 */
fun Project.getLocalProperty(propertyName: String): String {
    return getLocalProperty(propertyName, this)
}

/**
 * Obtain property declared on `$projectRoot/keystore.properties` file.
 *
 * @param propertyName the name of declared property
 */
fun Project.getKeystoreProperty(propertyName: String): String {
    return getKeystoreProperty(propertyName, this)
}

/**
 * Obtain property declared on `$projectRoot/keystore.properties` file.
 *
 * @param propertyName the name of declared property
 */
fun Project.getApiProperty(propertyName: String): String {
    return getApiProperty(propertyName, this)
}
