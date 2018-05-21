$(function() {
    // $('#connect-form-link').click(function(e) {
    //     $("#connect-form").delay(100).fadeIn(100);
    //     $("#register-form").fadeOut(100);
    //     $('#register-form-link').removeClass('active');
    //     $(this).addClass('active');
    //     e.preventDefault();
    // });
    //
    // $('#register-form-link').click(function(e) {
    //     $("#register-form").delay(100).fadeIn(100);
    //     $("#connect-form").fadeOut(100);
    //     $('#connect-form-link').removeClass('active');
    //     $(this).addClass('active');
    //     e.preventDefault();
    // });

    $('#setup-form-link').click(function(e) {
        $("#setup-form").delay(100).fadeIn(100);
        $("#overview-form").fadeOut(100);
        $('#overview-form-link').removeClass('current');
        $(this).addClass('current')
        e.preventDefault();
    });

    $('#overview-form-link').click(function(e) {
        $("#overview-form").delay(100).fadeIn(100);
        $("#setup-form").fadeOut(100);
        $('#setup-form-link').removeClass('current');
        $(this).addClass('current');
        e.preventDefault();
    });
});
