
var lastSelecMoves = [];
var isWhiteMove = true;
var isBlackMove = false;
var lastSelectPosition = "";

var whiteKingPosition = "e1";
var blackKingPosition = "e8";

var whiteKingMovement = 0;
var blackKingMovement = 0;

var whiteRookMovementAtA1 = 0;
var whiteRookMovementAtH1 = 0;

var blackRookMovementAtA8 = 0;
var blackRookMovementAtH8 = 0;

var checkPosition = "";

var isPawnPromotionSelection = false;

var allPositions = ["a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8",
   "a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
   "a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
   "a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
   "a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
   "a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
   "a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
   "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"
];

function selectBox(position) {

   if (!isPawnPromotionSelection) {
      var coin = document.getElementById(position).getAttribute("name");


      if (lastSelecMoves.length == 0) {

         if (coin.startsWith("W") && isBlackMove) {
            alert("BlackMove");
         }
         else if (coin.startsWith("B") && isWhiteMove) {
            alert("WhiteMove");
         }
         else {

            var moves = getMoves(position, coin, true);
            for (var i = 0; i < moves.length; i++) {
               document.getElementById(moves[i]).style.backgroundColor = 'green';
            }
            lastSelecMoves = moves;
            lastSelectPosition = position;
         }
      }
      else {
         clearLight();
         if (listContainsVariable(lastSelecMoves, position)) {
            moveCoins(position);
         }
         else {
            lastSelecMoves = [];
            lastSelectPosition = "";
            selectBox(position);
         }
      }
   }
}




function clearLight() {
   for (var i = 0; i < lastSelecMoves.length; i++) {
      document.getElementById(lastSelecMoves[i]).style.backgroundColor = '';
   }
}

function moveCoins(toPosition) {

   var coin = document.getElementById(lastSelectPosition).getAttribute("name");

   if (coin == "W_P" && toPosition.endsWith("8")) {
      isPawnPromotionSelection = true;
      showWhitePawnpromotionBox(toPosition);
   }

   else if (coin == "B_P" && toPosition.endsWith("1")) {
      isPawnPromotionSelection = true;
      showBlackPawnpromotionBox(toPosition);
   }

   else {
      var piece = document.getElementById(lastSelectPosition).innerHTML;
      document.getElementById(lastSelectPosition).innerHTML = '';
      document.getElementById(lastSelectPosition).name = "";


      document.getElementById(toPosition).innerHTML = piece;
      document.getElementById(toPosition).name = coin;



      if (coin == "W_K") {

         if (lastSelectPosition == "e1") {
            if (toPosition == "g1") {
               var whiteRook = document.getElementById("h1").innerHTML;

               document.getElementById("h1").innerHTML = '';
               document.getElementById("h1").name = "";

               document.getElementById("f1").innerHTML = whiteRook;
               document.getElementById("f1").name = "W_R";
               whiteRookMovementAtH1++;
            }

            if (toPosition == "c1") {
               var whiteRook = document.getElementById("a1").innerHTML;

               document.getElementById("a1").innerHTML = '';
               document.getElementById("a1").name = "";

               document.getElementById("d1").innerHTML = whiteRook;
               document.getElementById("d1").name = "W_R";
               whiteRookMovementAtA1++;
            }

         }

         whiteKingMovement++;
         whiteKingPosition = toPosition;
      }

      if (coin == "B_K") {
         if (lastSelectPosition == "e8") {
            if (toPosition == "g8") {
               var blackRook = document.getElementById("h8").innerHTML;

               document.getElementById("h8").innerHTML = '';
               document.getElementById("h8").name = "";

               document.getElementById("f8").innerHTML = blackRook;
               document.getElementById("f8").name = "B_R";
               blackRookMovementAtH8++;
            }

            if (toPosition == "c8") {
               var blackRook = document.getElementById("a8").innerHTML;

               document.getElementById("a8").innerHTML = '';
               document.getElementById("a8").name = "";

               document.getElementById("d8").innerHTML = blackRook;
               document.getElementById("d8").name = "W_R";
               blackRookMovementAtA8++;
            }

         }
         blackKingMovement++;
         blackKingPosition = toPosition;
      }

      if (coin == "W_R") {
         if (lastSelectPosition == 'a1') {
            whiteRookMovementAtA1++;
         }
         if (lastSelectPosition == 'h1') {
            whiteRookMovementAtH1++;
         }
      }

      if (coin == "B_R") {
         if (lastSelectPosition == 'a8') {
            blackRookMovementAtA8++;
         }
         if (lastSelectPosition == 'h8') {
            blackRookMovementAtH8++;
         }


      }

      isWhiteMove = !isWhiteMove;
      isBlackMove = !isBlackMove;

      lastSelecMoves = [];
      lastSelectPosition = "";

      checkStatus(coin);
   }

      
}

function getMoves(position, coin, flag) {

   var moves = [];

   if (coin.endsWith("N")) {
      moves = getKnightMoves(position, coin, flag);
   }
   if (coin.endsWith("R")) {
      moves = getRookMoves(position, coin, flag);
   }
   if (coin.endsWith("B")) {
      moves = getBishopMoves(position, coin, flag);
   }
   if (coin.endsWith("Q")) {
      moves = getQueenMoves(position, coin, flag);
   }
   if (coin.endsWith("K")) {
      moves = getKingMoves(position, coin, flag);
   }
   if (coin.endsWith("P")) {
      moves = getPawnMoves(position, coin, flag);
   }


   return moves;
}


function getPawnMoves(position, coin, flag) {
   var movingPlaces = [];

   if (coin.startsWith("W")) {
      var row = parseInt(position.charAt(1) + "") + 1;
      var col = String.fromCharCode(position.charCodeAt(0));

      if (row <= 8 && !isSameColourCoinHere(coin, col + "" + row) && !isOppositeColourCoinHere(coin, col + "" + row)) {
         var toPosition = col + "" + row;

         if (flag) {

            document.getElementById(position).name = "";
            document.getElementById(toPosition).name = coin;



            if (!isWhiteKingCheck()) {
               movingPlaces.push(toPosition);
            }

            document.getElementById(position).name = coin;
            document.getElementById(toPosition).name = "";
         }
         else {
            movingPlaces.push(toPosition);
         }

      }


      if (row == 3 && !isSameColourCoinHere(coin, col + "" + row) && !isOppositeColourCoinHere(coin, col + "" + row) && !isSameColourCoinHere(coin, col + "" + 4) && !isOppositeColourCoinHere(coin, col + "" + 4)) {

         if (flag) {

            document.getElementById(position).name = "";
            document.getElementById(col + "" + 4).name = coin;



            if (!isWhiteKingCheck()) {
               movingPlaces.push(col + "" + 4);
            }

            document.getElementById(position).name = coin;
            document.getElementById(col + "" + 4).name = "";
         }
         else {
            movingPlaces.push(toPosition);
         }
      }

      var rowMoves = [1, 1];
      var colMoves = [1, -1];

      for (var i = 0; i < 2; i++) {
         var col1 = String.fromCharCode(position.charCodeAt(0) + colMoves[i]);

         var row1 = parseInt(position.charAt(1) + "") + rowMoves[i];


         if (row1 <= 8 && col1 >= 'a' && col1 <= 'h' && isOppositeColourCoinHere(coin, col1 + "" + row1)) {

            var toPosition = col1 + "" + row1;

            if (flag) {

               var toCoin = document.getElementById(toPosition).getAttribute("name");

               document.getElementById(position).name = "";
               document.getElementById(toPosition).name = coin;



               if (!isWhiteKingCheck()) {
                  movingPlaces.push(toPosition);
               }

               document.getElementById(position).name = coin;
               document.getElementById(toPosition).name = toCoin;

            }
            else {
               movingPlaces.push(toPosition);
            }



         }

      }

   }

   if (coin.startsWith("B")) {
      var row = parseInt(position.charAt(1) + "") - 1;
      var col = String.fromCharCode(position.charCodeAt(0));

      if (row > 0 && !isSameColourCoinHere(coin, col + "" + row) && !isOppositeColourCoinHere(coin, col + "" + row)) {
         var toPosition = col + "" + row;

         if (flag) {

            document.getElementById(position).name = "";
            document.getElementById(toPosition).name = coin;



            if (!isBlackKingCheck()) {
               movingPlaces.push(toPosition);
            }

            document.getElementById(position).name = coin;
            document.getElementById(toPosition).name = "";
         }
         else {
            movingPlaces.push(toPosition);
         }

      }


      if (row == 6 && !isSameColourCoinHere(coin, col + "" + row) && !isOppositeColourCoinHere(coin, col + "" + row) && !isSameColourCoinHere(coin, col + "" + 5) && !isOppositeColourCoinHere(coin, col + "" + 5)) {
         if (flag) {
            document.getElementById(position).name = "";
            document.getElementById(col + "" + 5).name = coin;



            if (!isBlackKingCheck()) {
               movingPlaces.push(col + "" + 5);
            }

            document.getElementById(position).name = coin;
            document.getElementById(col + "" + 5).name = "";

         }
         else {
            movingPlaces.push(toPosition);
         }
      }

      var rowMoves = [-1, -1];
      var colMoves = [-1, 1];

      for (var i = 0; i < 2; i++) {
         var col1 = String.fromCharCode(position.charCodeAt(0) + colMoves[i]);

         var row1 = parseInt(position.charAt(1) + "") + rowMoves[i];


         if (row1 > 0 && col1 >= 'a' && col1 <= 'h' && isOppositeColourCoinHere(coin, col1 + "" + row1)) {

            var toPosition = col1 + "" + row1;

            if (flag) {

               var toCoin = document.getElementById(toPosition).getAttribute("name");

               document.getElementById(position).name = "";
               document.getElementById(toPosition).name = coin;



               if (!isBlackKingCheck()) {
                  movingPlaces.push(toPosition);
               }

               document.getElementById(position).name = coin;
               document.getElementById(toPosition).name = toCoin;

            }
            else {
               movingPlaces.push(toPosition);
            }



         }

      }

   }

   return movingPlaces;
}

function getKingMoves(position, coin, flag) {
   var movingPlaces = [];

   var rowMoves = [1, -1, 0, 0, 1, -1, 1, -1];
   var colMoves = [0, 0, 1, -1, 1, 1, -1, -1];


   for (var i = 0; i < 8; i++) {



      var col = String.fromCharCode(position.charCodeAt(0) + colMoves[i]);

      var row = parseInt(position.charAt(1) + "") + rowMoves[i];

      if (row > 0 && row <= 8 && col >= 'a' && col <= 'h' && !isSameColourCoinHere(coin, col + "" + row)) {

         var toPosition = col + "" + row;

         if (flag) {
            if (coin.startsWith("W")) {
               var toCoin = document.getElementById(toPosition).getAttribute("name");

               document.getElementById(position).name = "";
               document.getElementById(toPosition).name = coin;

               whiteKingPosition = toPosition

               if (!isWhiteKingCheck()) {
                  movingPlaces.push(toPosition);
               }
               whiteKingPosition = position
               document.getElementById(position).name = coin;
               document.getElementById(toPosition).name = toCoin;
            }
            if (coin.startsWith("B")) {

               var toCoin = document.getElementById(toPosition).getAttribute("name");

               document.getElementById(position).name = "";
               document.getElementById(toPosition).name = coin;

               blackKingPosition = toPosition

               if (!isBlackKingCheck()) {
                  movingPlaces.push(toPosition);
               }
               blackKingPosition = position
               document.getElementById(position).name = coin;
               document.getElementById(toPosition).name = toCoin;
            }
         }
         else {
            movingPlaces.push(toPosition);
         }



      }

   }

   if (flag) {
      if (coin.startsWith("W") && position == "e1" && !isWhiteKingCheck() && whiteKingMovement == 0) {

         if (document.getElementById("f1").getAttribute("name") == "" &&
            document.getElementById("g1").getAttribute("name") == "" && document.getElementById("h1").getAttribute("name") == "W_R"
            && whiteRookMovementAtH1 == 0) {
            whiteKingPosition = "f1"

            if (!isWhiteKingCheck()) {

               whiteKingPosition = "g1";

               if (!isWhiteKingCheck()) {
                  movingPlaces.push("g1");
               }
               whiteKingPosition = "f1";
            }
            whiteKingPosition = "e1";


         }

         if (document.getElementById("d1").getAttribute("name") == "" &&
            document.getElementById("c1").getAttribute("name") == "" &&
            document.getElementById("b1").getAttribute("name") == ""
            && document.getElementById("a1").getAttribute("name") == "W_R"
            && whiteRookMovementAtA1 == 0) {
            whiteKingPosition = "d1"

            if (!isWhiteKingCheck()) {

               whiteKingPosition = "c1";

               if (!isWhiteKingCheck()) {
                  movingPlaces.push("c1");
               }
               whiteKingPosition = "d1";
            }
            whiteKingPosition = "e1";


         }


      }









      if (coin.startsWith("B") && position == "e8" && !isBlackKingCheck() && blackKingMovement == 0) {

         if (document.getElementById("f8").getAttribute("name") == "" &&
            document.getElementById("g8").getAttribute("name") == "" && document.getElementById("h8").getAttribute("name") == "B_R"
            && blackRookMovementAtH8 == 0) {
            blackKingPosition = "f8"

            if (!isBlackKingCheck()) {

               blackKingPosition = "g8";

               if (!isBlackKingCheck()) {
                  movingPlaces.push("g8");
               }
               blackKingPosition = "f8";
            }
            blackKingPosition = "e8";


         }

         if (document.getElementById("d8").getAttribute("name") == "" &&
            document.getElementById("c8").getAttribute("name") == "" &&
            document.getElementById("b8").getAttribute("name") == ""
            && document.getElementById("a8").getAttribute("name") == "B_R"
            && blackRookMovementAtA8 == 0) {
            blackKingPosition = "d8"

            if (!isBlackKingCheck()) {

               blackKingPosition = "c8";

               if (!isBlackKingCheck()) {
                  movingPlaces.push("c8");
               }
               blackKingPosition = "d8";
            }
            blackKingPosition = "e8";


         }


      }


   }

   return movingPlaces;

}

function getQueenMoves(position, coin, flag) {
   var movingPlaces = getBishopMoves(position, coin, flag).concat(getRookMoves(position, coin, flag));
   return movingPlaces;
}


function getBishopMoves(position, coin, flag) {
   var movingPlaces = [];

   var rowMoves = [1, -1, 1, -1];
   var colMoves = [1, -1, -1, 1];

   for (var i = 0; i < 4; i++) {

      var col = String.fromCharCode(position.charCodeAt(0) + colMoves[i]);

      var row = parseInt(position.charAt(1)) + rowMoves[i];

      while (row > 0 && row <= 8 && col >= 'a' && col <= 'h' && !isSameColourCoinHere(coin, col + "" + row)) {

         var toPosition = col + "" + row;

         if (flag) {

            var toCoin = document.getElementById(toPosition).getAttribute("name");

            document.getElementById(position).name = "";
            document.getElementById(toPosition).name = coin;

            if ((coin.startsWith("W") && isWhiteKingCheck()) || (coin.startsWith("B") && isBlackKingCheck())) {
               document.getElementById(position).name = coin;
               document.getElementById(toPosition).name = toCoin;
               col = String.fromCharCode(col.charCodeAt(0) + colMoves[i]);
               row = parseInt(row + rowMoves[i]);
               continue;
            }

            document.getElementById(position).name = coin;
            document.getElementById(toPosition).name = toCoin;

         }


         if (isOppositeColourCoinHere(coin, toPosition)) {
            movingPlaces.push(toPosition);
            break;
         }

         else {
            movingPlaces.push(toPosition);
         }

         col = String.fromCharCode(col.charCodeAt(0) + colMoves[i]);

         row = parseInt(row + rowMoves[i]);

      }

   }

   return movingPlaces;

}

function getRookMoves(position, coin, flag) {
   var movingPlaces = [];

   var rowMoves = [1, -1, 0, 0];
   var colMoves = [0, 0, 1, -1];

   for (var i = 0; i < 4; i++) {

      var col = String.fromCharCode(position.charCodeAt(0) + colMoves[i]);

      var row = parseInt(position.charAt(1)) + rowMoves[i];

      while (row > 0 && row <= 8 && col >= 'a' && col <= 'h' && !isSameColourCoinHere(coin, col + "" + row)) {

         var toPosition = col + "" + row;

         if (flag) {

            var toCoin = document.getElementById(toPosition).getAttribute("name");

            document.getElementById(position).name = "";
            document.getElementById(toPosition).name = coin;

            if ((coin.startsWith("W") && isWhiteKingCheck()) || (coin.startsWith("B") && isBlackKingCheck())) {
               document.getElementById(position).name = coin;
               document.getElementById(toPosition).name = toCoin;
               col = String.fromCharCode(col.charCodeAt(0) + colMoves[i]);
               row = parseInt(row + rowMoves[i]);
               continue;
            }

            document.getElementById(position).name = coin;
            document.getElementById(toPosition).name = toCoin;

         }


         if (isOppositeColourCoinHere(coin, toPosition)) {
            movingPlaces.push(toPosition);
            break;
         }

         else {
            movingPlaces.push(toPosition);
         }

         col = String.fromCharCode(col.charCodeAt(0) + colMoves[i]);

         row = parseInt(row + rowMoves[i]);

      }

   }

   return movingPlaces;


}

function getKnightMoves(position, coin, flag) {
   var movingPlaces = [];

   var rowMoves = [1, 1, -1, -1, 2, 2, -2, -2];
   var colMoves = [2, -2, 2, -2, 1, -1, 1, -1];

   for (var i = 0; i < 8; i++) {



      var col = String.fromCharCode(position.charCodeAt(0) + colMoves[i]);

      var row = parseInt(position.charAt(1) + "") + rowMoves[i];

      if (row > 0 && row <= 8 && col >= 'a' && col <= 'h' && !isSameColourCoinHere(coin, col + "" + row)) {

         var toPosition = col + "" + row;

         if (flag) {

            var toCoin = document.getElementById(toPosition).getAttribute("name");

            document.getElementById(position).name = "";
            document.getElementById(toPosition).name = coin;



            if ((coin.startsWith("W") && !isWhiteKingCheck()) || (coin.startsWith("B") && !isBlackKingCheck())) {
               movingPlaces.push(toPosition);
            }

            document.getElementById(position).name = coin;
            document.getElementById(toPosition).name = toCoin;

         }
         else {
            movingPlaces.push(toPosition);
         }



      }

   }

   return movingPlaces;
}

function isSameColourCoinHere(coin, toPosition) {
   var toCoin = document.getElementById(toPosition).getAttribute("name");

   if (toCoin != "" && coin.startsWith(toCoin.charAt(0))) {
      return true;
   }
   return false;
}


function isOppositeColourCoinHere(coin, toPosition) {
   var toCoin = document.getElementById(toPosition).getAttribute("name");

   if (toCoin != "" && !coin.startsWith(toCoin.charAt(0))) {
      return true;
   }
   return false;
}


function isWhiteKingCheck() {

   for (var i = 0; i < allPositions.length; i++) {
      var coin = document.getElementById(allPositions[i]).getAttribute("name");


      if (coin.startsWith("B") && listContainsVariable(getMoves(allPositions[i], coin, false), whiteKingPosition)) {
         return true;
      }

   }

   return false;
}

function isBlackKingCheck() {

   for (var i = 0; i < allPositions.length; i++) {
      var coin = document.getElementById(allPositions[i]).getAttribute("name");


      if (coin.startsWith("W") && listContainsVariable(getMoves(allPositions[i], coin, false), blackKingPosition)) {
         return true;
      }

   }

   return false;
}

function listContainsVariable(list, variable) {
   for (var i = 0; i < list.length; i++) {
      if (list[i] == variable) {
         return true;
      }
   }

   return false;
}

function isWhiteKingStaleMate() {

   for (var i = 0; i < allPositions.length; i++) {
      var coin = document.getElementById(allPositions[i]).getAttribute("name");

      if (coin.startsWith("W") && getMoves(allPositions[i], coin, true).length > 0) {
         return false;
      }

   }
   return true;
}


function isBlackKingStaleMate() {

   for (var i = 0; i < allPositions.length; i++) {
      var coin = document.getElementById(allPositions[i]).getAttribute("name");

      if (coin.startsWith("B") && getMoves(allPositions[i], coin, true).length > 0) {
         return false;
      }

   }
   return true;
}

function isMatchDraw() {
   blackCoins = [];
   whiteCoins = [];

   for (var i = 0; i < allPositions.length; i++) {
      var coin = document.getElementById(allPositions[i]).getAttribute("name");

      if (coin.startsWith("W")) {
         whiteCoins.push(coin);
      }

      if (coin.startsWith("B")) {
         blackCoins.push(coin);
      }

   }


   if (blackCoins.length == 1 && listContainsVariable(blackCoins, "B_K") && whiteCoins.length == 1 && listContainsVariable(whiteCoins, "W_K")) {
      return true;
   }

   return false;

}


function showBlackPawnpromotionBox(toPosition) {


   var div = document.createElement("div");
   document.body.appendChild(div);
   div.id = "promotion";

   var rook = document.createElement("button");
   var queen = document.createElement("button");
   var bishop = document.createElement("button");
   var knight = document.createElement("button");

   rook.className = "promotioncoins";
   queen.className = "promotioncoins";
   bishop.className = "promotioncoins";
   knight.className = "promotioncoins";

   rook.id = "B_R";
   queen.id = "B_Q";
   bishop.id = "B_B";
   knight.id = "B_N";


   rook.addEventListener('click', function () { givePromotion(toPosition, 'B_R') });
   queen.addEventListener('click', function () { givePromotion(toPosition, 'B_Q') });
   bishop.addEventListener('click', function () { givePromotion(toPosition, 'B_B') });
   knight.addEventListener('click', function () { givePromotion(toPosition, 'B_N') });

   rook.innerHTML = '&#9820';
   queen.innerHTML = '&#9819';
   bishop.innerHTML = '&#9821';
   knight.innerHTML = '&#9822';


   div.appendChild(rook);
   div.appendChild(queen);
   div.appendChild(bishop);
   div.appendChild(knight);

   document.getElementById("fullboard").style.filter = 'blur(10px)';

}

function showWhitePawnpromotionBox(toPosition) {
   var div = document.createElement("div");
   document.body.appendChild(div);
   div.id = "promotion";

   var rook = document.createElement("button");
   var queen = document.createElement("button");
   var bishop = document.createElement("button");
   var knight = document.createElement("button");



   rook.className = "promotioncoins";
   queen.className = "promotioncoins";
   bishop.className = "promotioncoins";
   knight.className = "promotioncoins";

   rook.id = "W_R";
   queen.id = "W_Q";
   bishop.id = "W_B";
   knight.id = "W_N";

   rook.addEventListener('click', function () { givePromotion(toPosition, 'W_R') });
   queen.addEventListener('click', function () { givePromotion(toPosition, 'W_Q') });
   bishop.addEventListener('click', function () { givePromotion(toPosition, 'W_B') });
   knight.addEventListener('click', function () { givePromotion(toPosition, 'W_N') });


   rook.innerHTML = '&#9814';
   queen.innerHTML = '&#9813';
   bishop.innerHTML = '&#9815';
   knight.innerHTML = '&#9816';

   div.appendChild(rook);
   div.appendChild(queen);
   div.appendChild(bishop);
   div.appendChild(knight);

   document.getElementById("fullboard").style.filter = 'blur(10px)';
}

function givePromotion(toPosition, coin) {
   document.getElementById(lastSelectPosition).innerHTML = '';
   document.getElementById(lastSelectPosition).name = "";
   document.getElementById(toPosition).name = coin;
   document.getElementById(toPosition).innerHTML = document.getElementById(coin).innerHTML;
   isPawnPromotionSelection = false;
   document.getElementById("fullboard").style.filter = '';
   document.getElementById("promotion").remove();
   isWhiteMove = !isWhiteMove;
   isBlackMove = !isBlackMove;

   lastSelecMoves = [];
   lastSelectPosition = "";

   checkStatus(coin);
}

function checkStatus(lastMovedCoin) {
   if (isMatchDraw()) {
      alert("Match Drawn");
      location.reload();
   }

   else if (lastMovedCoin.startsWith("W")) {


      if (!isBlackKingCheck()) {
         if (checkPosition != "") {
            document.getElementById(checkPosition).style.backgroundColor = '';
            checkPosition = "";
         }

         if (isBlackKingStaleMate()) {
            alert("Stalemate");
            location.reload();
         }

      }
      else {
         document.getElementById(blackKingPosition).style.backgroundColor = 'red';
         checkPosition = blackKingPosition;
         if (isBlackKingStaleMate()) {
            alert("White win");
            location.reload();
         }
      }



   }


   else if (lastMovedCoin.startsWith("B")) {

      if (!isWhiteKingCheck()) {
         if (checkPosition != "") {
            document.getElementById(checkPosition).style.backgroundColor = '';
            checkPosition = "";
         }
         if (isWhiteKingStaleMate()) {
            alert("Stalemate");
            location.reload();
         }
      }
      else {
         document.getElementById(whiteKingPosition).style.backgroundColor = 'red';
         checkPosition = whiteKingPosition;
         if (isWhiteKingStaleMate()) {
            alert("Black win");
            location.reload();

         }
      }


   }
}