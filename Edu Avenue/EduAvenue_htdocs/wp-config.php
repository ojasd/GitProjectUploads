<?php
/**
 * The base configurations of the WordPress.
 *
 * This file has the following configurations: MySQL settings, Table Prefix,
 * Secret Keys, and ABSPATH. You can find more information by visiting
 * {@link http://codex.wordpress.org/Editing_wp-config.php Editing wp-config.php}
 * Codex page. You can get the MySQL settings from your web host.
 *
 * This file is used by the wp-config.php creation script during the
 * installation. You don't have to use the web site, you can just copy this file
 * to "wp-config.php" and fill in the values.
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('DB_NAME', 'bitnami_wordpress');

/** MySQL database username */
define('DB_USER', 'root');

/** MySQL database password */
define('DB_PASSWORD', '');

/** MySQL hostname */
define('DB_HOST', 'localhost');

/** Database Charset to use in creating database tables. */
define('DB_CHARSET', 'utf8');

/** The Database Collate type. Don't change this if in doubt. */
define('DB_COLLATE', '');

/**#@+
 * Authentication Unique Keys and Salts.
 *
 * Change these to different unique phrases!
 * You can generate these using the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}
 * You can change these at any point in time to invalidate all existing cookies. This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY',         '6c90061d87920e78ba92add9ea6c7d4905ba93571fa09c48f43721248c9e0d68');
define('SECURE_AUTH_KEY',  'b76d8b9596531ad238de210f29faf8ef30ff134e3b1316217e779ea6f16f164b');
define('LOGGED_IN_KEY',    'ac52b4b473ad2cb7195ec052b065d6911f1e7cb591ff489d44ad719a34be7b3f');
define('NONCE_KEY',        'ce0f398242a394bd5e1001c4398d481d778de75eea4d0da8fdd057baf874c2bd');
define('AUTH_SALT',        '7024048226fc6f31abcea6af1a606465f365309bcf480518475bfbc9b4393d78');
define('SECURE_AUTH_SALT', 'fcd44821c5e3d8398511fcc27925beb930684bfcae55cc1a98228c4654b94b37');
define('LOGGED_IN_SALT',   '96f3143991fd92ac068e8f58cb0715c09cfb87148c4dafb925533b337e2e2160');
define('NONCE_SALT',       '5b9abc46a1416495a839906e1568b7549f90aac90ee058990c855129059baf93');

/**#@-*/

/**
 * WordPress Database Table prefix.
 *
 * You can have multiple installations in one database if you give each a unique
 * prefix. Only numbers, letters, and underscores please!
 */
$table_prefix  = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 */
define('WP_DEBUG', false);

/* That's all, stop editing! Happy blogging. */
/**
 * The WP_SITEURL and WP_HOME options are configured to access from any hostname or IP address.
 * If you want to access only from an specific domain, you can modify them. For example:
 *  define('WP_HOME','http://example.com');
 *  define('WP_SITEURL','http://example.com');
 *
*/

/*define('WP_SITEURL', 'http://' . $_SERVER['HTTP_HOST'] . '/wordpress');
define('WP_HOME', 'http://' . $_SERVER['HTTP_HOST'] . '/wordpress');
*/

/** Absolute path to the WordPress directory. */
if ( !defined('ABSPATH') )
	define('ABSPATH', dirname(__FILE__) . '/');

/** Sets up WordPress vars and included files. */
require_once(ABSPATH . 'wp-settings.php');

//define('WP_TEMP_DIR', 'C:/xampp/Xampp/apps/wordpress/tmp');

