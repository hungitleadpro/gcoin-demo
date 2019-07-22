pragma solidity ^0.4.0;

contract Gcoin {

    mapping(address => uint) balances;

    event Transfer(address indexed from, address indexed to, uint256 _value);

    function addCoin(address receiver, uint amount) returns (bool sufficient) {
        balances[receiver] += amount;
        return true;
    }
    function Gcoin() {
        balances[tx.origin] = 0;
    }

    function sendCoin(address send, address receiver, uint amount) returns (bool sufficient) {
        if (balances[send] < amount) return false;
        balances[send] -= amount;
        balances[receiver] += amount;
        Transfer(send, receiver, amount);
        return true;
    }

    function reduceCoin(address receiver, uint amount) returns (bool sufficient) {
        if (balances[receiver] < amount) return false;
        balances[receiver] -= amount;
        return true;
    }

    function getBalance(address addr) returns (uint) {
        return balances[addr];
    }

}
