// Global variable to store the action to perform when "Yes" is clicked
let pendingAction = null;

/**
 * Shows a custom confirmation modal
 * @param {string} message - The text to display
 * @param {function} action - The function to run if the user clicks "Yes"
 */

function confirmAction(message, action) {
    document.getElementById('confirmMessageText').innerText = message;
    pendingAction = action;

    // Get existing instance or create a new one only if needed
    let confirmModalElement = document.getElementById('confirmModal');
    let confirmModal = bootstrap.Modal.getInstance(confirmModalElement);

    if (!confirmModal) {
        confirmModal = new bootstrap.Modal(confirmModalElement);
    }

    confirmModal.show();
}
/**
 * Shows a standard status notification
 */
function showStatus(title, message) {
    document.getElementById('modalTitle').innerText = title;
    document.getElementById('modalMessage').innerText = message;

    const statusModal = new bootstrap.Modal(document.getElementById('statusModal'));
    statusModal.show();
}

// Global listener for the "Yes" button
document.addEventListener('DOMContentLoaded', () => {
    const confirmBtn = document.getElementById('confirmBtn');
    if (confirmBtn) {
        confirmBtn.onclick = async () => {
            if (pendingAction) {
                await pendingAction(); // Run the stored function
                const modal = bootstrap.Modal.getInstance(document.getElementById('confirmModal'));
                if (modal) modal.hide();
                pendingAction = null; // Clear it
            }
        };
    }
});