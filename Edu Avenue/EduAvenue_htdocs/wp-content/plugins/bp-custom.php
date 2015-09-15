<?php
// hacks and mods will go here
/**
 * Disables BuddyPress' registration process and fallsback to WordPress' one.
 */
function my_disable_bp_registration() {
  remove_action( 'bp_init',    'bp_core_wpsignup_redirect' );
  remove_action( 'bp_screens', 'bp_core_screen_signup' );
}
add_action( 'bp_loaded', 'my_disable_bp_registration' );

add_filter( 'bp_get_signup_page', "firmasite_redirect_bp_signup_page");
    function firmasite_redirect_bp_signup_page($page ){
        return bp_get_root_domain() . '/wp-signup.php'; 
    }
?>