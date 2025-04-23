<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <style>
        body {
font-family: Arial, sans-serif;
margin: 0;
padding: 0;
background-color: #f4f4f4;
display: flex;
justify-content: center;
align-items: center;
height: 100vh;
}

.container {
width: 100%;
max-width: 400px;
background-color: #000;
color: white;
padding: 40px;
border-radius: 10px;
box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.content {
font-size: 16px;
line-height: 1.6;
}

.footer {
margin-top: 30px;
text-align: center;
font-size: 12px;
}
</style>
</head>

<body>
<div class="container">
        <div class="content">
            <p>Dear User,</p>
            <p>We are delighted to inform you that your account registration was successful. Here are your account details:</p>
            <ul>
                <li><strong>Email:</strong> ${userEmail}</li>
                <li><strong>Password:</strong> <span style="font-weight: bold; background-color: #333; padding: 4px 8px; border-radius: 4px;">${userPassword}</span></li>
            </ul>
            <p>Please keep your credentials secure. If you have any questions or need further assistance, feel free to contact our support team.</p>
            <p>Best regards,</p>
            <p>Biddu Auction Team</p>
        </div>
        <div class="footer">
            <p>&copy; 2023 ! Biddu Auction Team. All rights reserved.</p>
        </div>
    </div>
</body>

</html>
