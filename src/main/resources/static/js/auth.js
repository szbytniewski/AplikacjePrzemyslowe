document.addEventListener("DOMContentLoaded", function() {
    console.log("Authentication script loaded.");

    fetch("/user-info", { cache: "no-store" })
        .then(response => response.json())
        .then(data => {
            console.log("User info response:", data);
            if (data.authenticated) {
                const userEmail = document.getElementById('user-email');
                const loginButton = document.getElementById('login-button');
                const logoutButton = document.getElementById('logout-button');

                if (userEmail) {
                    userEmail.textContent = data.email;
                    userEmail.style.display = 'inline';
                }

                if (loginButton) {
                    loginButton.style.display = 'none';
                }

                if (logoutButton) {
                    logoutButton.style.display = 'inline';
                }
            } else {
                const loginButton = document.getElementById('login-button');
                const logoutButton = document.getElementById('logout-button');

                if (loginButton) {
                    loginButton.style.display = 'inline';
                }

                if (logoutButton) {
                    logoutButton.style.display = 'none';
                }
            }
        })
        .catch(error => console.error("Error fetching user info:", error));
});