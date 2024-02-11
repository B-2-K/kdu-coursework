document.addEventListener('DOMContentLoaded', function () {
    const profileButton = document.getElementById('profile-button');
    const profileDetails = document.getElementsByClassName('mobile-navigation')[0];
    const otherDetails = document.getElementById('other-details');

    profileButton.addEventListener('click', function () {
        if (profileDetails.style.display === 'none' || profileDetails.style.display === '') {
            profileDetails.style.display = 'block';
            otherDetails.style.display = 'none';
        } else {
            profileDetails.style.display = 'none';
            otherDetails.style.display = 'block';
        }
    });
});


document.addEventListener('DOMContentLoaded', function () {
    const profileButton = document.getElementById('mobile-post-open-button');
    const profileDetails = document.getElementsByClassName('mobile-tweet-section')[0];
    const otherDetails = document.getElementById('other-details');

    profileButton.addEventListener('click', function () {
        if (profileDetails.style.display === 'none' || profileDetails.style.display === '') {
            profileDetails.style.display = 'block';
            otherDetails.style.display = 'none';
        } else {
            profileDetails.style.display = 'none';
            otherDetails.style.display = 'block';
        }
    });
});


